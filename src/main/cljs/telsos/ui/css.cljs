(ns telsos.ui.css
  (:require
   [clojure.string :as str]
   [clojure.walk]))

;; IMPL.
(defrecord ^:private Twc [form str])
(defn- twc? [x] (instance? Twc x))

(defn- atomic-form->str [form]
  (cond
    (string?            form) form
    (implements? INamed form) (name form)
    (twc?               form) (:str form)

    :else (throw (ex-info "non-atomic" {:form form}))))

(defn- form->str [form]
  (when-not (coll? form)
    (throw (ex-info "not a collection" {:form form})))

  (letfn [(transform [x]
            (cond
              (map? x) (seq x)
              (set? x) (seq x)
              :else    x))

          (split-by-space [s] (str/split s #"\s"))]

    (->> form
         (clojure.walk/postwalk transform)
         (flatten)
         (map  atomic-form->str)
         (mapcat split-by-space)
         (distinct)
         (sort)
         (str/join " "))))

;; FACADE
(defn twc [classes]
  (if (twc? classes) classes (->Twc classes (form->str classes))))

(defn twcs [form]
  (:str (if (twc? form) form (twc form))))
