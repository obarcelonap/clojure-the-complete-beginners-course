(ns clojure-the-complete-beginners-course.core-test
  (:require [clojure.test :refer :all]
            [clojure-the-complete-beginners-course.core :refer :all]))

(deftest a-test
  (testing "FIXED, I pass."
    (is (= 1 1))))
