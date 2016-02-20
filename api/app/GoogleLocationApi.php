<?php
/**
 * Created by PhpStorm.
 * User: kostja93
 * Date: 20.02.16
 * Time: 13:39
 */

namespace app;


class GoogleLocationApi {
    private $baseUrl =  "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

    public function __construct($key) {
        $url ="&location=41.3,2.1&radius=5000";
        $this->baseUrl .= "key=" . $key;
    }

    public function getLocations($lat, $lon) {

    }

}