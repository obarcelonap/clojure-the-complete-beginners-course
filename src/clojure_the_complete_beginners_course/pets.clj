(ns clojure-the-complete-beginners-course.pets
  (:gen-class))

(defn age-in-human-years
  [pet-type]
  (def pet-year-factors {'dog 7, 'cat 5, 'fish 10})
  (fn [pet-age] (* pet-age (get pet-year-factors pet-type))))

