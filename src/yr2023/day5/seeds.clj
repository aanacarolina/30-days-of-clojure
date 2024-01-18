(ns yr2023.day5.seeds
  (:require [clojure.string :as str]))


(defn input->data [input]
  (str/split-lines (slurp input)))

(defn parse-data 
  "convert string to data structure"
  [data]
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

(defn convert-seed [seed maps]
  (loop [remaining-maps maps
         a-seed (atom seed)]
    (if (empty? remaining-maps)
      @a-seed
      (do
        (let [current-vector (first remaining-maps) 
            dst (get current-vector 0)
            src (get current-vector 1)
            rng (get current-vector 2)] 
        (when (some #(= @a-seed %) (or (range  dst (+ dst rng)) (range src (+ src rng))))
          (reset! a-seed (+ @a-seed (- dst src)) )))
      (recur (rest remaining-maps)
              @a-seed)))))


(defn smallest-location [input]
  (->> (input->data input)
       parse-data
       #_convert-maps
       #_(min)))

(comment
  (smallest-location "inputs/day5_exinput.txt")
  (convert-seed 79 '([50 98 2]
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