(ns clojure-the-complete-beginners-course.store-test
  (:require [clojure.test :refer :all]
            [clojure-the-complete-beginners-course.store :refer :all]
            [clojure-the-complete-beginners-course.bank :refer [clean-accounts create-account]])
  )


(deftest store-test
  (def customer-account "customer-account")
  (clean-accounts)
  (create-store-account)
  (create-account customer-account 100000)
  (testing "buy-item"
    (testing "Can't buy unknown items"
      (is (= (buy-item "bla" 1 customer-account) "item-not-found"))
      )
    (testing "Can buy pens"
      (is (= (buy-item "pen" 3 customer-account) 3))
      )
    (testing "Can buy notebooks"
      (is (= (buy-item "notebook" 2 customer-account) 10))
      )
    (testing "Can buy backpacks"
      (is (= (buy-item "backpack" 4 customer-account) 40))
      )
    )
  )
