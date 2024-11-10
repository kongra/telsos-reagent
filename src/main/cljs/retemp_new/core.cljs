(ns retemp-new.core
  (:require
   ["react" :as React]
   ["react-dom/client" :as react-client]
   [reagent.core :as r]))

;; APP COMPONENTS
(defn- html-root []
  (println "retemp-new")
  [:div.sample "Hello retemp-new"])

;; APP INSTRUMENTATION
(defn- render-root!
  [root]
  (->> [:> React/StrictMode [html-root]]
       (r/as-element)
       (.render root)))

(defonce root-atom (atom nil))

(defn after-load! []
  (render-root! @root-atom))

(defn start! []
  (->> "core"
       (js/document.getElementById)
       (react-client/createRoot)
       (reset! root-atom)
       (render-root!)))
