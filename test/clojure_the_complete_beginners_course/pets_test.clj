(ns clojure-the-complete-beginners-course.pets-test
  (:require [clojure.test :refer :all]
            [clojure-the-complete-beginners-course.pets :refer :all]))

(deftest age-in-human-years-test
  (testing "Age in human years"
    (testing "Dog age should have a factor of 7"
      (is (= ((age-in-human-years 'dog) 2) 14)))
    (testing "Cat age should have a factor of 5"
      (is (= ((age-in-human-years 'cat) 2) 10)))
    (testing "Fish age should have a factor of 10"
      (is (= ((age-in-human-years 'fish) 2) 20)))
    )
  )
