<?php
/**
 * Created by PhpStorm.
 * User: kostja93
 * Date: 20.02.16
 * Time: 13:39
 */

namespace App;


class GoogleLocationApi {
    private $baseUrl =  "https://maps.googleapis.com/maps/api/place/";
    private $key;

    public function __construct($key) {
        $this->key = $key;
    }

    public function get($lat, $lon, $radius) {
        $locations = $this->getLocations($lat,$lon, $radius);
        return $this->updateLocations($locations);

        return $locations;
    }

    public function getLocations($lat, $lon, $radius) {
        $url = $this->baseUrl . "nearbysearch/json?key=" . $this->key . "&location=$lat,$lon&radius=$radius&type=bar";

        $rawData = file_get_contents($url);

        $data = json_decode($rawData);

        if(strtolower($data->status) == "ok") {
            return $data->results;
        }

        return [];
    }

    public function updateLocations($locations) {
        $results = [];
        foreach($locations as $location) {
            $dbLoc = Location::where('lat', $location->geometry->location->lat)
                ->where('lon', $location->geometry->location->lng)
                ->where('name', $location->name);

            if($dbLoc->count() == 0) {
                $newLoc = new Location();
                $newLoc->name = $location->name;

                if(isset($location->photos[0]->photo_reference))
                    $newLoc->picture = $this->savePicture($location->photos[0]->photo_reference);

                $newLoc->lat  = $location->geometry->location->lat;
                $newLoc->lon  = $location->geometry->location->lng;

                $newLoc->save();
                $results[] = $newLoc;
            } else {
                $results[] = $dbLoc->first();
            }
        }

        return $results;
    }

    public function savePicture($ref) {
        return $this->baseUrl . "photo?maxwidth=400&photoreference=$ref&key=". $this->key;
    }

}