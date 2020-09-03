(ns clojure-the-complete-beginners-course.car-dealership-test
  (:require [clojure.test :refer :all]
            [clojure-the-complete-beginners-course.car-dealership :refer :all]))

(deftest car-prices-test
  (testing "Car prices"
    (testing "Checks for budget"
      (testing "Can't offer cars when budget lower than min car price"
        (is (= (count (get-car-prices 0)) 0))
        )
      (testing "Provides cars where price is in the budget"
        (let [prices (get-car-prices 80000)]
          (is (= (count prices) 2))
          (is (= prices '(["Fiat" 20000] ["BMW" 60000])))
          )
        )
      (testing "Provides cars where price is in the budget after applying the coupon"
        (let [prices (get-car-prices 50000 "SAVE20")]
          (is (= (count prices) 2))
          (is (= prices '(["Fiat" 16000.0] ["BMW" 48000.0])))
          )
        )
      (testing "Provides all cars when budget is bigger than max car price"
        (is (= (count (get-car-prices 10000000))))
        )
      )
    )
  )