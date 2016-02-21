<?php

namespace App\Http\Controllers;

use App\Rating;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;

class RatingController extends Controller
{
    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $rating = new Rating();

        $rating->user_id = $request->get('user_id');
        $rating->location_id = $request->get('location_id');
        $rating->price_id = $request->get('price_id');
        $rating->equipment_id = $request->get('equipment_id');
        $rating->atmosphere_id = $request->get('atmosphere_id');
        $rating->stars = $request->get('stars');

        try{
            $rating->save();
            return $rating;
        } catch(\Exception $e) {
            return $this->errorMsg($e->getMessage());
        }
    }

}
