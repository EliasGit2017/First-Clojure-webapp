(ns basic-webapp.views
  (:require [basic-webapp.db :as db]
            [clojure.string :as str]
            [hiccup.page :as page]
            [hiccup.core :as hic]
            [ring.util.anti-forgery :as util]
            [hickory.core :as h])
  (:use markdown.core))

;;---------------------------------------------------------------------------------------
(defn readfile
  "Returns a sequence from a file f"
  [f]
  (with-open [rdr (clojure.java.io/reader f)]
    (doall (line-seq rdr))))

;; Functions designed to take markdown file(s) as input to display
;; md files are transformed into HTML string to produce a hiccup string.
 
(def mydata (readfile "./hickory_material.md"))

(def datamd (->> (subvec (into [] mydata) 0 (dec (count mydata)))
                 (clojure.string/join "\n")
                 md-to-html-string
                 h/parse
                 h/as-hiccup))

;; Functions designed to take an HTML file as input and return a hiccup string

(def myhtmldata (readfile "./template.html"))

(def datahtml (->> myhtmldata
                   clojure.string/join
                   h/parse
                   h/as-hiccup))

;;---------------------------------------------------------------------------------------

(defn gen-page-head
  [title]
  [:head
   [:title (str "Locations: " title)]
   (page/include-css "/css/styles.css")])

(def header-links
  [:div#header-links
   "[ "
   [:a {:href "/"} "Home"]
   " | "
   [:a {:href "/add-location"} "Add a Location"]
   " | "
   [:a {:href "/all-locations"} "View All Locations"]
   " ]"])

(defn home-page
  []
  (page/html5
   (gen-page-head "Home")
   header-links
   (first datamd)))

(defn add-location-page
  []
  (page/html5
   (gen-page-head "Add a Location")
   header-links
   [:h1 "Add a Location"]
   [:form {:action "/add-location" :method "POST"}
    (util/anti-forgery-field) ; prevents cross-site scripting attacks
    [:p "x value: " [:input {:type "text" :name "x"}]]
    [:p "y value: " [:input {:type "text" :name "y"}]]
    [:p [:input {:type "submit" :value "submit location"}]]]))

(defn add-location-results-page
  [{:keys [x y]}]
  (let [id (db/add-location-to-db x y)]
    (page/html5
     (gen-page-head "Added a Location")
     header-links
     [:h1 "Added a Location"]
     [:p "Added [" x ", " y "] (id: " id ") to the db. "
      [:a {:href (str "/location/" id)} "See for yourself"]
      "."])))

(defn location-page
  [loc-id]
  (let [{x :x y :y} (db/get-xy loc-id)]
    (page/html5
     (gen-page-head (str "Location" loc-id))
     header-links
     [:h1 "A Single Location"]
     [:p "id: " loc-id]
     [:p "x: " x]
     [:p "y: " y])))

(defn all-locations-page
  []
  (let [all-locs (db/get-all-locations)]
    (page/html5
     (gen-page-head "All Locations in the db")
     header-links
     [:h1 "All Locations"]
     [:table
      [:tr [:th "id"] [:th "x"] [:th "y"]]
      (for [loc all-locs]
        [:tr [:td (:id loc)] [:td (:x loc)] [:td (:y loc)]])])))