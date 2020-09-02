(ns clojure-the-complete-beginners-course.car-dealership
  (:gen-class))

(defn car-prices
  [budget & [coupon]]
  (def available-cars {"Fiat" 20000, "BMW" 60000, "Ferrari" 100000})
  (filter (fn [[_ price]] (>= budget price)) available-cars)
  )