(ns basic-webapp.handler
  (:require [compojure.core :refer :all]
            [basic-webapp.views :as views]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (views/home-page))
  (GET "/add-location" [] (views/add-location-page))
  (POST "/add-location" 
    {params :params}
    (views/add-location-results-page params))
  (GET "/location/:loc-id"
    [loc-id]
    (views/location-page loc-id))
  (GET "/all-locations"
    []
    (views/all-locations-page))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
