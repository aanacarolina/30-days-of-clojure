(ns yr2023.day4.hailstones
  (:require [clojure.string :as str]))

(defn input->data [input]
  (str/split-lines (slurp input)))

(defn split-position-velocity [data]
 (str/split data #"\s*@\s*"))

(defn p-v->long [line]
  (into [] (map parse-long (str/split line #"\s*,\s*"))))


(comment 
  
  (def data (input->data "day24_input.txt"))
  (map split-position-velocity data)
  (map p-v->long  ["19, 13, 30" "-2,  1, -2"])
  (map + [19 13 30] [-2 1 -2])

  (map + [1 2 3] [1 2 3])
  )