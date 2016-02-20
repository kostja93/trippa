<?php

namespace App\Http\Controllers;

use App\Like;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;

class LikeController extends Controller
{
    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $like = new Like();

        $like->user_id = $request->get("user_id");
        $like->location_id = $request->get("location_id");
        $like->like = $request->get("like");

        try {
            $like->save();
            return ["status" => true];
        } catch(\Exception $e) {
            return $this->errorMsg();
        }
    }

}
