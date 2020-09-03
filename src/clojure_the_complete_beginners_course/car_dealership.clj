(ns clojure-the-complete-beginners-course.car-dealership
  (:gen-class))

(def coupons {"SAVE20" 0.2})
(def cars '(["Fiat" 20000] ["BMW" 60000] ["Ferrari" 100000]))

(defn get-car-prices-by-coupon
  [coupon]
  (def discount-factor (get coupons coupon 0))
  (map
    (fn [[car price]] [car (- price (* price discount-factor))])
    cars
    )
  )

(defn get-car-prices
  [budget & [coupon]]
  (def available-cars
    (if coupon
      (get-car-prices-by-coupon coupon)
      cars
      )
    )
  (filter (fn [[_ price]] (>= budget price)) available-cars)
  )