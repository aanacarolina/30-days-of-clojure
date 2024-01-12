(ns yr2023.day5.seeds
   (:require [clojure.string :as str]))


 (defn input->data [input]
   (str/split-lines (slurp input)))

 (defn parse-data [data]
   "convert string to data structure"
   data)
 ;blank line
 ;name of maps
 ;sequences of nbrs
 
 ;EOF = nil (end condition for parsing)

 (defn convert-maps [parsed-data]
   "converts numbers from a source category into numbers in a destination category"
   parsed-data)

(desired parsed data format
 {:seeds [79 14 55 13]
 :categories-conversion-maps 
  {:seed-to-soil [[50 98 2] [52 50 48]]
  :soil-to-fertilizer [[0 15 37][37 52 2][39 0 15]]
  :fertilizer-to-water [[][][][]]
  }
  })
 
;each map has same [index 0 = destination-start / index 1 = source-start / index 2 = range] 
;[50 98 2] range 2 
;50 = 98 
;51 = 99

;[52 50 48] - range 48
;52 = 50
;100 = 98
;but map can have different ammount of conversion instructions
 
 ;conversion formula: 
 
 ;location is the result of all conversions
(defn seeds [input]
  (->> (input->data input)))

(comment
  (seeds "inputs/day5_exinput.txt"))
#_(destination range start
               source range start
               range length)

