<?php

use App\Atmosphere;
use App\Equipment;
use App\Location;
use App\Price;
use Illuminate\Database\Seeder;

class PricesTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {

        (new Price(['desc' => 'small']))->save();
        (new Price(['desc' => 'medium']))->save();
        (new Price(['desc' => 'large']))->save();

        (new Atmosphere(['desc' => 'small']))->save();
        (new Atmosphere(['desc' => 'medium']))->save();
        (new Atmosphere(['desc' => 'large']))->save();

        (new Equipment(['desc' => 'small']))->save();
        (new Equipment(['desc' => 'medium']))->save();
        (new Equipment(['desc' => 'large']))->save();
    }
}
