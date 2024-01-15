(ns yr2023.day5.seeds
  (:require [clojure.string :as str]))


(defn input->data [input]
  (str/split-lines (slurp input)))

(defn parse-data [data]
  "convert string to data structure"
  (let [seeds (first (map #(re-seq  #"\d+" %) data))
        int-maps (->> (rest data)
                      (map #(re-seq #"\d+" %))
                      (remove nil?)
                      (remove empty?)
                      (map #(->> % (map parse-long) vec)))]
    {:seeds (vec (map parse-long seeds))
     :maps int-maps}))


;each map vector has [index 0 = dst / index 1 = src / index 2 = rng]
;(some #(= seed %) (or (range  dst (+ dst rng)) (range src (+ src rng)))) 
;call again, but seed value will now be
;if nil/false = current value of seed
;if true seed = (+ seed (dst - src))
;if end of array = last seed value
;save location
;return seq w/ all locations

(defn convert-maps [seed maps]
  "converts numbers from a source category into numbers in a destination category"
  (loop [map (first maps)]
    (if (seq? map)
      (let [dst (get map 0)
            src (get map 1)
            rng (get map 2)]
        (if
         (= true (some #(= seed %) (or (range  dst (+ dst rng)) (range src (+ src rng)))))
          (+ seed (dst - src))
          seed))
      (recur (rest maps)))))


(defn smallest-location [input]
  (->> (input->data input)
       parse-data
       #_convert-maps
       #_(min)))

(comment
  (smallest-location "inputs/day5_exinput.txt")
  (convert-maps 79 '([50 98 2]
                     [52 50 48]
                     [0 15 37]
                     [37 52 2]
                     [39 0 15]
                     [49 53 8]
                     [0 11 42]
                     [42 0 7]
                     [57 7 4]
                     [88 18 7]
                     [18 25 70]
                     [45 77 23]
                     [81 45 19]
                     [68 64 13]
                     [0 69 1]
                     [1 0 69]
                     [60 56 37]
                     [56 93 4]))
  )


#_(desired parsed data format
           {:seeds [79 14 55 13]
            :categories-conversion-maps
            {:seed-to-soil [[50 98 2] [52 50 48]]
             :soil-to-fertilizer [[0 15 37] [37 52 2] [39 0 15]]
             :fertilizer-to-water [[] [] [] []]}}
           decided to go
           {:seeds [79 14 55 13],
            :maps
            ([50 98 2]
             [52 50 48]
             [0 15 37]
             [37 52 2]
             [39 0 15]
             [49 53 8]
             [0 11 42]
             [42 0 7]
             [57 7 4]
             [88 18 7]
             [18 25 70]
             [45 77 23]
             [81 45 19]
             [68 64 13]
             [0 69 1]
             [1 0 69]
             [60 56 37]
             [56 93 4])})