Possible permissions:


permission:android.car.permission.BIND_CAR_INPUT_SERVICE
permission:android.car.permission.CAR_MILEAGE
permission:android.car.permission.CONTROL_CAR_EXTERIOR_LIGHTS
permission:android.car.permission.CAR_DRIVING_STATE
permission:android.car.permission.VMS_SUBSCRIBER
permission:android.car.permission.CAR_NAVIGATION_MANAGER
permission:android.car.permission.CONTROL_CAR_DOORS
permission:android.car.permission.CONTROL_CAR_CLIMATE
permission:android.car.permission.CONTROL_CAR_SEATS
permission:android.car.permission.CAR_VENDOR_EXTENSION
permission:android.car.permission.CAR_EXTERIOR_ENVIRONMENT
permission:com.android.car.permission.CAR_HANDLE_USB_AOAP_DEVICE
permission:android.car.permission.CAR_ENERGY_PORTS
permission:android.car.permission.CONTROL_CAR_MIRRORS
permission:android.car.permission.ADJUST_CAR_CABIN
permission:android.car.permission.CONTROL_CAR_WINDOWS
permission:com.android.car.permission.READ_CAR_STEERING
permission:android.car.permission.CAR_CONTROL_AUDIO_VOLUME
permission:android.car.permission.VMS_PUBLISHER
permission:com.android.car.settings.SET_INITIAL_LOCK
permission:android.car.permission.STORAGE_MONITORING
permission:android.car.permission.CAR_TIRES
permission:android.car.permission.CAR_DISPLAY_IN_CLUSTER
permission:android.car.permission.CAR_DIAGNOSTICS
permission:com.android.car.permission.RECEIVE_CAR_AUDIO_DUCKING_EVENTS
permission:android.car.permission.CAR_INSTRUMENT_CLUSTER_CONTROL
permission:com.android.car.permission.ACCESS_CAR_PROJECTION_STATUS
permission:android.car.permission.CAR_IDENTIFICATION
permission:android.car.permission.CAR_EXTERIOR_LIGHTS
permission:android.car.permission.CAR_POWERTRAIN
permission:com.android.car.permission.CAR_POWER
permission:android.car.permission.BIND_INSTRUMENT_CLUSTER_RENDERER_SERVICE
permission:com.google.android.gms.carsetup.DRIVING_MODE_MANAGER
permission:com.android.car.permission.CONTROL_CAR_INTERIOR_LIGHTS
permission:android.car.permission.CAR_MOCK_VEHICLE_HAL
permission:android.car.permission.CAR_DYNAMICS_STATE
permission:com.android.car.permission.CONTROL_CAR_DISPLAY_UNITS
permission:com.android.car.permission.READ_CAR_INTERIOR_LIGHTS
permission:android.car.permission.CAR_ENGINE_DETAILED
permission:android.car.permission.CONTROL_APP_BLOCKING
permission:android.car.permission.CLEAR_CAR_DIAGNOSTICS
permission:android.car.permission.CAR_CONTROL_AUDIO_SETTINGS
permission:android.car.permission.CAR_PROJECTION
permission:android.car.permission.CAR_INFO




Speed: PERMISSION_SPEED  : Done
Gear: PERMISSION_POWERTRAIN: Done
Night mode: PERMISSION_EXTERIOR_ENVIRONMENT
Fuel level: PERMISSION_ENERGY
ABS: PERMISSION_CAR_DYNAMICS_STATE


info about how/where to find specific information for sensor data : https://developer.android.com/reference/android/car/hardware/CarSensorManager


# Sensor service manager Car.SENSOR_SERVICE supports:

PARKING_BRAKE_ON
public static final int PARKING_BRAKE_ON
Parking brake state.

Constant Value: 287310850 (0x11200402)

GEAR_SELECTION
public static final int GEAR_SELECTION
Currently selected gear This is the gear selected by the user.

Constant Value: 289408000 (0x11400400)

IGNITION_STATE
public static final int IGNITION_STATE
Represents ignition state

Constant Value: 289408009 (0x11400409)


WHEEL_TICK
public static final int WHEEL_TICK
Reports wheel ticks

Constant Value: 290521862 (0x11510306)


PERF_VEHICLE_SPEED
public static final int PERF_VEHICLE_SPEED
Speed of the vehicle

Constant Value: 291504647 (0x11600207)







Here you can find all the permission that we can have access to :D
https://developer.android.com/reference/android/car/Car
