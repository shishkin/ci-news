(ns ci-news.app
  (:use [domina :only [by-id set-text!]]
        [domina.events :only [listen!]]))

(defn ^:export start []
  (let [tagline (by-id "tagline")]
    (listen! tagline :click (fn [evt] (js/alert "button clicked!")))))

