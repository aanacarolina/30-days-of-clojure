(ns yr2023.day5.seeds
  (:require [clojure.string :as str]))


(defn input->data [input]
  (str/split-lines (slurp input)))

(defn all-seeds [data]
  (map parse-long (first (map #(re-seq  #"\d+" %) data))))

(defn map-content [data]
  (take-while (complement empty?) (nthrest (rest data) 2)))

(defn map-content->integer [content]
  (mapv (fn [inner-vector]
          (mapv #(parse-long %) inner-vector))
        content))

(defn map-name [data]
  (second (rest data)))

(defn map-category [data]
  (let [map-content  (into [] (map #(re-seq #"\d+" %) (map-content data)))]
  (map-content->integer map-content))) 


(defn parsed-data [data]
  {:seeds (all-seeds data)
   :all-maps {:map-name (map-name data) :map-content (map-category data)}})


#_(defn parse-data 
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
         value seed]
    (if (empty? remaining-maps)
      value
      (do
        (let [current-vector (first remaining-maps) 
            dst (get current-vector 0)
            src (get current-vector 1)
            rng (get current-vector 2)] 
        (if (some #(= value %) (or (range  dst (+ dst rng)) (range src (+ src rng))))
          (reset! value (+ value (- dst src)) )))
      (recur (rest remaining-maps)
              value)))))

#_(defn is-in-range? [input-value range] 
 (let [[dest-start src-start range-length] range]
   (println "dest" dest-start "src" src-start "lenght" range-length
            )
   (<= src-start input-value dest-start)))

;(<= start-value input-value end-value)


(defn smallest-location [input]
  (->> (input->data input)
       parse-data
       #_convert-maps
       #_(min)))

(comment
  (smallest-location "inputs/day5_exinput.txt")
  (def data (input->data "inputs/day5_exinput.txt"))
  (all-seeds data) 
  (map-category data)
  (parsed-data data) 
  )

(
#_(initial desired parsed data format
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
             [56 93 4])}))