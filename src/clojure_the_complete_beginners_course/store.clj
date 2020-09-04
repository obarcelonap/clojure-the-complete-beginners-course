(ns clojure-the-complete-beginners-course.store
  :require [clojure-the-complete-beginners-course.bank] :refer [transaction])

(def items {"pen" 1, "notebook" 5, "backpack" 10})

(defn buy-item
  ([item]
  (def price (get items item))
  (if !price
    "item-not-found"
    (buy-item item price)
    )
   )
  ([item price]
    (transaction price (str "bought " item " at the store"))
    )
  )
