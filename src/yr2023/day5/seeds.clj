(ns yr2023.day5.seeds)


(defn sum-odds [start end]
  (->> (range start end)
       (filter odd?)
       (reduce +)))

(defn sum-odds2 [start end]
  (reduce + (filter odd? (range start end))))


  #_(destination range start
                 source range start
                 range length)

