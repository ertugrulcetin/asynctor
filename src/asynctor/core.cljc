(ns asynctor.core
  #?(:clj (:require [clojure.core.async :as async]))
  #?(:cljs (:require [cljs.core.async :as async])))


(defn inspect
  [ch]
  #?(:clj  {:buffer (or (some-> ch .buf .buf seq reverse vec) [])
            :puts (mapv second (.puts ch))
            :takes (vec (.takes ch))}
     :cljs {:buffer (or (some-> ch .-buf .-buf .-arr (js->clj :keywordize-keys true)) [])
            :puts   (loop [r []]
                      (if-some [v (.pop (.-puts ch))]
                        (recur (conj r (.-val v)))
                        r))
            :takes  (loop [r []]
                      (if-some [v (.pop (.-takes c))]
                        (recur (conj r v))
                        r))}))


(defn buffer
  [ch]
  (:buffer (inspect ch)))


(defn puts
  [ch]
  (:puts (inspect ch)))


(defn takes
  [ch]
  (:takes (inspect ch)))


(comment
  ;;=> {:buffer [0 1 2 3 4 5 6 7 8 9], :puts [], :takes []}
  (let [c (async/chan 10)]
    (dotimes [x 10]
      (async/>!! c x))
    (inspect c))

  (let [c (async/chan)]
    (dotimes [x 10]
      (async/put! c x))
    (puts c))

  (let [c (async/chan)]
    (dotimes [x 10]
      (async/take! c (fn [] )))
    (takes c)))