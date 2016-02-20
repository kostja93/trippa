<?php
/**
 * Created by PhpStorm.
 * User: kostja93
 * Date: 20.02.16
 * Time: 15:59
 */

namespace App;


class SkyScannerHotels {

    protected $baseUrl = "http://partners.api.skyscanner.net/apiservices/hotels/liveprices/v2/";
    protected $key;

    public function __construct($key) {
        $this->key = $key;
    }

    public function get($lat, $lon, $market = 'UK', $currency = 'EUR', $locale = 'en-GB', $guests = 1, $rooms = 1) {
        $entityid = $lat.",".$lon."-latlong";
        $apiKey = $this->key;
        $checkindate = (new \DateTime())->format("Y-m-d");
        $checkoutdate = (new \DateTime())->add(new \DateInterval("P1D"))->format("Y-m-d");
        $urlExtension = "$market/$currency/$locale/$entityid/$checkindate/$checkoutdate/$guests/$rooms?apiKey=$apiKey";

        $rawData = file_get_contents($this->baseUrl . $urlExtension);
        $data = json_decode($rawData);

        return $this->manipulateData($data->hotels);
    }

    public function manipulateData($hotels) {
        $result = [];
        foreach($hotels as $hotel) {
            $result[] = [
                "name" => $hotel->name,
                "maps" => "https://www.google.de/maps/search/".$hotel->latitude.",".$hotel->longitude
            ];
        }

        return $result;
    }
}