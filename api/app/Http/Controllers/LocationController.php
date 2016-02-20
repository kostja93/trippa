<?php

namespace App\Http\Controllers;

use App\GoogleLocationApi;
use App\Location;
use App\SkyScannerHotels;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;

class LocationController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return Location::all();
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $location = new Location();

        $location->name = $request->get('name');
        $location->picture = $request->get('picture');
        $location->lat = $request->lat('lat');
        $location->lon = $request->lon('lon');

        try {
            $location->save();
            return $location;
        } catch(\Exception $e) {
            return $this->errorMsg();
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $location = Location::find($id);

        if($location)
            return $location->first();
        return $this->errorMsg();
    }

    public function locations($lat, $lon, $radius = 1000) {
        $gal = new GoogleLocationApi(env('GOOGLE_API'));
        return $gal->get($lat, $lon, $radius);
    }

    public function hotels($lat, $lon) {
        $ssh = new SkyScannerHotels(env('SKY_SCANNER_API'));

        return $ssh->get($lat, $lon);
    }
}
