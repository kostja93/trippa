<?php

namespace App\Http\Controllers;

use App\GoogleLocationApi;
use App\Location;
use App\User;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;

class UserController extends Controller
{
    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $user = new User();

        $user->name = $request->get('name');

        try {
            $user->save();
            return $user;
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
        try {
            $user = User::find($id);
            if ($user) {
                /** @var User $user */
                $user = $user->firstOrFail();
                return [
                    "user" =>$user,
                    "settings" => $user->setting
                ];
            } else
                return [];
        }catch (\Exception $e) {
            return $this->errorMsg();
        }
    }

    public function locations($lat, $lon, $radius = 1000) {
        $gal = new GoogleLocationApi(env('GOOGLE_API'));
        return $gal->get($lat, $lon, $radius);
    }
}
