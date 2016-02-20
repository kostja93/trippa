<?php

use App\Like;
use App\Location;
use App\Rating;
use App\User;
use Illuminate\Database\Seeder;

class DefaultData extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $this->insertUser();
        $this->insertLocation();
        $this->insertLikes();
        $this->insertRatings();
    }

    public function insertUser() {
        for($i = 0; $i < 50; $i++){
            $user = new User();

            $user->name = str_random();

            $user->save();
        }
    }

    public function insertLocation() {
        for($i = 0; $i < 50; $i++){
            $user = new Location();

            $user->name = str_random();
            $user->picture = str_random();
            $user->lat = rand(0, 50);
            $user->lon = rand(0, 50);

            $user->save();
        }
    }

    public function insertLikes() {
        for($i = 0; $i < 50; $i++){
            $like = new Like();

            $like->location_id = rand(0, 49);
            $like->user_id = rand(0, 49);
            $like->like = rand(0, 1);

            $like->save();
        }
    }

    public function insertRatings() {
        for($i = 0; $i < 50; $i++){
            $like = new Rating();

            $like->location_id = rand(0, 49);
            $like->user_id = rand(0, 49);
            $like->price_id = rand(0, 2);
            $like->equipment_id = rand(0, 2);
            $like->atmosphere_id = rand(0, 2);

            $like->save();
        }
    }
}
