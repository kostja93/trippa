<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Rating extends Model
{
    protected $fillable = ['location_id', 'user_id', 'price_id', 'equipment_id', 'atmosphere_id'];

    public function location() {
        return $this->belongsTo(Location::class);
    }

    public function user() {
        return $this->belongsTo(User::class);
    }

    public function price() {
        return $this->belongsTo(Price::class);
    }

    public function equipment() {
        return $this->belongsTo(Equipment::class);
    }

    public function atmosphere() {
        return $this->belongsTo(Atmosphere::class);
    }
}
