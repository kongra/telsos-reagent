(ns ^:figwheel-always retemp.core
  (:require
   [reagent.core :as     r]
   [reagent.dom  :as  rdom]
   [cljs.core.async
    :refer [<! >! put! chan timeout]])

  (:require-macros
   [cljs.core.async.macros
    :refer [go alt! go-loop]]))

(enable-console-print!)

(defrecord Person
    [email
     first-name
     last-name
     age])

;; VIEW
(defn hidbutton
  [label]
  (let [isHidden (r/atom false)]
    (fn []
      (when-not @isHidden
        [:button.btn.btn-warning
         {:on-click #(reset! isHidden true) :type "button"} label]))))

(def ^:private counter     (r/atom 0))
(def ^:private click-dates (r/atom []))

(defn- inc-counter! []
  (swap! counter inc)
  (swap! click-dates conj (new js/Date)))

(defn- reset-counter! []
  (reset! counter 0)
  (reset! click-dates []))

(defn- html-date-li [date key]
  [:li {:key key} (str date)])

(defn- react-keys []
  (iterate inc 0))

;; 1000000
(defn main-view []
  [:div.container
   [:h2 (str "You clicked " @counter " times.")]
   [:button.btn.btn-success {:on-click inc-counter!   :type "button"} "Add One"]
   [:button.btn.btn-danger  {:on-click reset-counter! :type "button"} "Zero"]
   [hidbutton "Hide me !!!"]
   [hidbutton "Hide me too !!!"]

   [:br]

   [:ul (map html-date-li @click-dates (react-keys))]])

;; INSTRUMENTATION

(defn on-js-reload
  []
  (println "Reloaded...")
  (rdom/render [main-view]
    (. js/document (getElementById "app"))))

(defn init
  []
  (on-js-reload))

(defonce start
  (init))


(defn test-1 []
 (go
   (println "Wykonuję działanie 1")
   (<! (timeout 1000))
   (println "Wykonuję działanie 2")
   (<! (timeout 2000))
   (println "Wykonuję działanie 3")
   (<! (timeout 2000))
   (println "Wykonuję działanie 4")))

















(let [c (chan)]
  (go
    (println "--2-start")
    (<! (timeout 3000))
    (>! c 15)
    (>! c 16)
    (println "--2-end"))

  (go
    (println "--1-start")
    (<! (timeout 6000))
    (println "--1" (<! c))
    (println "--1" (<! c))
    (println "--1-end")))



#_(go
    (.log js/console "Hello")
    (<! (timeout 2000))
    (.log js/console "async")
    (<! (timeout 2000))
    (.log js/console "world!"))

#_(let [c (chan)
        t (timeout 2000)]
    (go
      (alt!
        c (.log js/console "Odebrałem wartość z kanału c")
        t (.log js/console "Upłynął czas 2s")))

    (go
      (>! c 23)))

#_(let [c (chan)
        t (timeout 2000)]
    (go
      (alt!
        c ([v] (.log js/console (str "Odebrałem wartość " v " z kanału c")))
        t ([v] (.log js/console (str "Upłynął czas 2s po których odebrałem " v)))))

    ;; (go (<! (timeout 1000)) (>! c 23))
    ;; (go (<! (timeout  900)) (>! c 24))
    ;; (go (<! (timeout  200)) (>! c 25))
    ;; (go (<! (timeout  356)) (>! c 26))

    (go (<! (timeout 1000)) (>! c 23)
        (<! (timeout  900)) (>! c 24)
        (<! (timeout  200)) (>! c 25)
        (<! (timeout  356)) (>! c 26)))

#_(let [c (chan)]
    (go-loop []
      (.log js/console (str "Odczytałem " (<! c)))
      (recur))

    (go (<! (timeout 1000)) (>! c 23)
        (<! (timeout  900)) (>! c 24)
        (<! (timeout  200)) (>! c 25)
        (<! (timeout  356)) (>! c 26)))

#_(defn events-chan
    [element event-type]
    (let [c (chan 100)]
      (.addEventListener element event-type (fn [e] (put! c e)))
      c))

#_(let [mouse-c (events-chan js/window "mousemove")]
    (go
      (while true
        (.log js/console (<! mouse-c)))))
