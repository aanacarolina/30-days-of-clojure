(ns advent-of-code.day3.gear-ratios
  (:require [clojure.string :as str]))

;parse 
;split by digits juntos
;ponto
;anything que nao seja ponto e digit



;criar matriz/map
'([{:x 0, :y 0, :value 467 :type :number}
   {:x 3, :y 0, :value 114 :type :number}]
  [{:x 3, :y 1, :value "*" :type :symbol}]
  [{:x 0, :y 2, :value 35 :type :number}
   {:x 3, :y 2, :value 633 :type :number}]
  [{:x 2, :y 3, :value "#" :type :symbol}]
  [{:x 0, :y 4, :value 617 :type :number}
   {:x 1, :y 4, :value "*" :type :symbol}]
  [{:x 6, :y 5, :value "+" :type :symbol}
   {:x 7, :y 5, :value 58 :type :number}]
  [{:x 2, :y 6, :value 592 :type :number}]
  [{:x 6, :y 7, :value 755 :type :number}]
  [{:x 3, :y 8, :value 664 :type :number}
   {:x 4, :y 8, :value 598 :type :number}])

;story 
;x = line 
;y = column

;is-adjacent - 1st narrow down by line of symbol is +1/-1 in relation to the number

;filter by is-adjacent?

;sum total

(defn input->data [file]
  (str/split-lines (slurp file)))

(defn parse-data [data]
  (let [[all digit others] (re-find #"(\d+) (.*)" data)]
    [all digit others]))

;;(loop )

(defn is-adjacent? [data])

(comment
  (input->data "day3_exinput.txt")
  
  (map parse-data ["467..114.."
 "...*......"
 "..35..633."
 "......#..."
 "617*......"
 ".....+.58."
 "..592....."
 "......755."
 "...$.*...."
 ".664.598.."])
  )
