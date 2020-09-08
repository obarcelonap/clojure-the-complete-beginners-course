(ns clojure-the-complete-beginners-course.store
  (:require [clojure-the-complete-beginners-course.bank :refer [create-account transaction]])
  )

(def store-account-name "STORE-ACCOUNT")
(defn create-store-account [] (create-account store-account-name 0))
(def items {"pen" 1, "notebook" 5, "backpack" 10})

(defn buy-item
  ([item quantity dst-account-name]
   (def price (get items item))
   (if (nil? price)
     "item-not-found"
     (do
       (def amount (* quantity price))
       (def transaction-result (transaction dst-account-name store-account-name amount))
       (if (= transaction-result "transferred") amount transaction-result)
       )
     )
   )
  )


