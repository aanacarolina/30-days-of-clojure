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

#_(desired parsed data format
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
  (seeds "inputs/day5_exinput.txt")

;(some #(= seed %) (or (range  dst (+ dst rng)) (range src (+ src rng)))) 
;call again, but seed value will now be
;if nil/false = current value of seed
;if true (+ seed (dst - src))
;if eol = return location =  last seed value
;  

  (some #(= seed %) (or (range  dst (+ dst rng)) (range src (+ src rng))))

  ;each map has same [index 0 = dst / index 1 = src / index 2 = rng]

  ;1 seed
  79
  ;2 seed-to-soil
  (some #(= 79 %) (or (range  50 (+ 50 2)) (range 98 (+ 98 2))))
  79
  (some #(= 79 %) (or (range  52 (+ 52 48)) (range 50 (+ 50 48))))
  (+ 79 (- 52 50))

  ;3 soil-to-fertilizer
  (some #(= 81 %) (or (range  0 (+ 0 37)) (range 15 (+ 15 37))))
  (some #(= 81 %) (or (range  37 (+ 37 2)) (range 52 (+ 52 2))))
  (some #(= 81 %) (or (range  39 (+ 39 15)) (range 0 (+ 0 15))))

  ;4 fertilizer-to-water
  (some #(= 81 %) (or (range  49 (+ 49 8)) (range 53 (+ 53 8))))
  (some #(= 81 %) (or (range  0 (+ 0 42)) (range 11 (+ 11 42))))
  (some #(= 81 %) (or (range  42 (+ 42 7)) (range 0 (+ 0 7))))
  (some #(= 81 %) (or (range  57 (+ 57 4)) (range 7 (+ 7 4))))

  ;5 water-to-light
  (some #(= 81 %) (or (range  88 (+ 88 7)) (range 18 (+ 18 7))))
  (some #(= 81 %) (or (range  18 (+ 18 70)) (range 25 (+ 25 70))))
  (+ 81 (- 18 25))

  ;6 light-to-temperature
  (some #(= 74 %) (or (range  45 (+ 45 23)) (range 77 (+ 77 23))))
  (some #(= 74 %) (or (range  81 (+ 81 19)) (range 45 (+ 45 19))))
  (some #(= 74 %) (or (range  68 (+ 68 13)) (range 64 (+ 64 13))))
  (+ 74 (- 68 64))

  ;7 temperature-to-humidity
  (some #(= 78 %) (or (range  0 (+ 0 1)) (range 69 (+ 69 1))))
  (some #(= 78 %) (or (range  1 (+ 1 69)) (range 0 (+ 0 69))))

  ;8 humidity-to-location
  (some #(= 78 %) (or (range  60 (+ 60 37)) (range 56 (+ 56 37))))
  (+ 78 (- 60 56))
  (some #(= 82 %) (or (range  56 (+ 56 4)) (range 93 (+ 93 4))))
  (+ 82 (- 56 93))
  ;location = 82  
  
  )
#_(destination range start
               source range start
               range length)

