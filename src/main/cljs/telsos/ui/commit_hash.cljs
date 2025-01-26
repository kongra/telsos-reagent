(ns telsos.ui.commit-hash
  (:require
   [cljs-http.client :as http]
   [cljs.core.async :refer [<! go]]
   [clojure.string :as str]
   [reagent.core :as r]))

(def ^:private commit-hash-atom (r/atom nil))

(defn load-commit-hash!
  []
  (go
    (try
      (let [response (<! (http/get ".commit_hash" {:with-credentials? false}))]
        (if (= 200 (:status response))
          (let [commit-hash (-> response :body clojure.string/trim)]
            (reset! commit-hash-atom commit-hash)
            (println "Loaded commit-hash" commit-hash))

          (println "Failed to load commit hash. Status:" (:status response))))

      (catch js/Error e
        (println "Error loading commit hash:" e)))))

(defonce ^:private _onetime (load-commit-hash!))

;; FACADE
(defn value [] (str @commit-hash-atom))

(defn ensured-value []
  (let [commit-hash (value)]
    (when (str/blank? commit-hash)
      (throw (ex-info "commit-hash unavailable" {})))

    commit-hash))
