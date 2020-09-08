(ns clojure-the-complete-beginners-course.bank)

(def accounts (ref {}))

(defstruct account :name :amount)

(defn clean-accounts
  []
  (dosync
    (ref-set accounts {})
    )
  )

(defn account-exists?
  [name]
  (contains? @accounts name))

(defn get-account
  [name]
  (get @accounts name))

(defn change-account-amount
  [account amount]
  (def account-name (:name account))
  (assoc account :amount amount)
  )

(defn create-account
  [name amount]
  (if (account-exists? name)
    "account-already-exists"
    (dosync
      (def new-account (struct account name amount))
      (ref-set accounts (assoc @accounts name new-account))
      name
      )
    )
  )

(defn transaction
  [src-account-name dst-account-name amount]
  (def src-account (get-account src-account-name))
  (def dst-account (get-account dst-account-name))
  (def new-src-account-amount
    (if (some? src-account)
      (- (:amount src-account) amount)
      0
      )
    )

  (cond
    (nil? src-account) "src-account-not-exists"
    (nil? dst-account) "dst-account-not-exists"
    (<= new-src-account-amount 0) "not-enough-money-in-src-account"
    :else (do
            (def new-src-account (change-account-amount src-account new-src-account-amount))
            (def new-dst-account (change-account-amount dst-account (+ (:amount dst-account) amount)))
            (dosync
              (ref-set accounts (assoc @accounts src-account-name new-src-account))
              (ref-set accounts (assoc @accounts dst-account-name new-dst-account))
              )
            "transferred"
            )
    )
  )