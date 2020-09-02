(ns clojure-the-complete-beginners-course.car-dealership-test
  (:require [clojure.test :refer :all]
            [clojure-the-complete-beginners-course.car-dealership :refer :all]))

(deftest car-prices-test
  (testing "Car prices"
    (testing "Checks for budget"
      (testing "Can't offer cars when budget lower than min car price"
        (is (= (count (car-prices 0)) 0))
        )
      (testing "Provides cars where price is in the budget"
        (is (= (count (car-prices 80000)) 2))
        )
      (testing "Provides all cars when budget is bigger than max car price"
        (is (= (count (car-prices 10000000)) 3))
        )
      )
    )
  )