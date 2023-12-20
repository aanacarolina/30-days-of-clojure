(ns advent-of-code.day3.gear-ratios
  (:require [clojure.string :as str]))

;parse 
;split by digits juntos
;ponto
;anything que nao seja ponto e digit

;criar matriz/map
(def data-map '({:x 0, :y 0, :value "467", :type :digit}
               {:x 5, :y 0, :value "114", :type :digit}
               {:x 3, :y 1, :value "*", :type :symbol}
               {:x 2, :y 2, :value "35", :type :digit}
               {:x 6, :y 2, :value "633", :type :digit}
               {:x 6, :y 3, :value "#", :type :symbol}
               {:x 0, :y 4, :value "617", :type :digit}
               {:x 3, :y 4, :value "*", :type :symbol}
               {:x 5, :y 5, :value "+", :type :symbol}
               {:x 7, :y 5, :value "58", :type :digit}
               {:x 2, :y 6, :value "592", :type :digit}
               {:x 6, :y 7, :value "755", :type :digit}
               {:x 3, :y 8, :value "$", :type :symbol}
               {:x 5, :y 8, :value "*", :type :symbol}
               {:x 1, :y 9, :value "664", :type :digit}
               {:x 5, :y 9, :value "598", :type :digit}))

#_'([{:x 0, :y 0, :value 467 :type :number}
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

;(+ x (count (re-find #"\d+" s)))
;(parse-long (str s (take-while #(Character/isDigit %) s))) 


(defn parse-data [y s]
  (loop [result []
         x 0]
    (cond
      ;checks if eol
      (>= x (count s))
      result
      ;checks if dot
      (= (subs s x (inc x)) ".")
      (recur
       result
       (inc x))
      ;checks if digit
      (-> s
          (.charAt x); add the number of 
          Character/isDigit)
      (let [v (apply str (take-while #(Character/isDigit %) (subs s x)))]
        (recur
         (conj result
               {:x x
                :y y
                :value v
                :type :digit})
         (+ x (count v))))
      ;checks if symbol (actually anything other than eol, digit or dot))
      :else
      (recur
       (conj result {:x x :y y :value (subs s x (inc x)) :type :symbol})
       (inc x)))))

(defn digits-map [data-map]
  (filter #(= (:type %) :digit) data-map))

(defn symbols-map [data-map]
  (filter #(= (:type %) :symbol) data-map))

(defn digit [digit-map]
  (:value digit-map))

(defn digits-width [digit]
  (count digit))

(defn is-adjacent? [data])

(defn process-gears [input]
  (->> (input->data input)
       (map-indexed parse-data)
       (reduce concat)
       
       ))

(comment

  (process-gears "day3_exinput.txt")

  (process-gears "day3_input.txt")
  
  (digits-map data-map)
  (symbols-map data-map)

  (digits-width (digit {:x 1, :y 9, :value "664", :type :digit}))
    
  (-> "467.+..114..."
      (.charAt 5)
      Character/isDigit)
   ;Same thing
  (number? (parse-long (subs "467.+..114..." 5 (inc 5))))


  )







