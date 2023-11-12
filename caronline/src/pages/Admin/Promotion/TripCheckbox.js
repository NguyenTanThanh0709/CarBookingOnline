import React from 'react';

function TripCheckbox({ trip, selectedTrips, handleCheckboxChange }) {
  return (
    <div key={trip.id} className="mb-2">
      <input
        type="checkbox"
        id={`trip_${trip.id}`}
        name={`trip_${trip.id}`}
        value={trip.id}
        onChange={handleCheckboxChange}
        className="mr-2"
        checked={selectedTrips.includes(trip.id)}
      />
      <label htmlFor={`trip_${trip.id}`}>{trip.location}</label>
    </div>
  );
}

export default TripCheckbox;
