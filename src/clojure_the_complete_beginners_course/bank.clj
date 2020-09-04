(ns clojure-the-complete-beginners-course.bank)

(def accounts (atom {}))

(defstruct account :name :amount)

(defn clean-accounts
  []
  (swap! accounts {}))

(defn account-exists?
  [name]
  (contains? @accounts name))

(defn get-account
  [name]
  (get @accounts name))

(defn change-account-amount
  [account amount]
  (def account-name (:name account))
  (def new-account (assoc account :amount amount))
  (swap! accounts assoc account-name new-account)
  )

(defn create-account
  [name amount]
  (if (account-exists? name)
    "account-already-exists"
    (do
      (def new-account (struct account name amount))
      (swap! accounts assoc name new-account)
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
    :else (
            do (
                (change-account-amount src-account new-src-account-amount)
                (change-account-amount dst-account (+ (:amount dst-account) amount))
                "transferred"
                )
               )
    )
  )