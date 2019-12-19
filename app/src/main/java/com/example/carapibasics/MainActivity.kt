package com.example.carapibasics

import android.app.Activity
import android.car.Car
import android.car.CarInfoManager
import android.car.hardware.CarSensorEvent
import android.car.hardware.CarSensorManager
import android.content.ComponentName
import android.content.ServiceConnection
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView





class MainActivity : Activity() {

    private lateinit var car : Car
    private val permissions = arrayOf(Car.PERMISSION_SPEED, Car.PERMISSION_POWERTRAIN, Car.PERMISSION_ENERGY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCar()
    }

    override fun onResume() {
        super.onResume()

        var allPermissionsGranted = true
        for (perm in permissions) {
            if (checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false
                break
            }
        }
        if(allPermissionsGranted) {
            if (!car.isConnected && !car.isConnecting) {
                car.connect()
            }
        } else {
            Log.i("permission", "requesting permission")
            requestPermissions(permissions, 0)
        }

    }

    override fun onPause() {
        if(car.isConnected) {
            car.disconnect()
        }

        super.onPause()
    }

    private fun initCar() {
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_AUTOMOTIVE)) {
            return
        }

        if(::car.isInitialized) {
            return
        }

        car = Car.createCar(this, object : ServiceConnection {
            override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                onCarServiceReady()
            }

            override fun onServiceDisconnected(componentName: ComponentName) {
                // on failure callback
            }
        })
    }

    private fun onCarServiceReady() {
        val sensorManager = car.getCarManager(Car.SENSOR_SERVICE) as CarSensorManager

        Log.i("support", sensorManager.supportedSensors.toList().toString())
        /// watch all sensors supported by sensor service
        watchSpeedSensor(sensorManager)
        watchGearSensor(sensorManager)
        watchParkingBreak(sensorManager)
//        watchIgnitionState(sensorManager) // error problems
        watchFuelLevel(sensorManager)
        watchChargingRate(sensorManager)
        watchBatteryLevel(sensorManager)
        //watchRpm(sensorManager) Still doesnt work requires engine permissions



















        //watchSpeedMilage(sensorManager)
        //watchOilLevel(sensorManager)

    }

    private fun watchRpm(sensorManager: CarSensorManager) {
        sensorManager.registerListener(
            { carSensorEvent ->
                Log.i("rpm", carSensorEvent.floatValues[0].toString() + " rpm")

            },
            CarSensorManager.SENSOR_TYPE_RPM,
            CarSensorManager.SENSOR_RATE_NORMAL

        )

    }

    private fun watchChargingRate(sensorManager: CarSensorManager) {
        sensorManager.registerListener(
            { carSensorEvent ->

                var chargeRateTextView = findViewById<TextView>(R.id.chargeRateTextView)
                val chargeRate = carSensorEvent.floatValues[0]

                Log.i("chargeRate", chargeRate.toString() + " mW")
                chargeRateTextView.text = "BatteryLevel: " + chargeRate.toString() + "mW"

            },
            CarSensorManager.SENSOR_TYPE_EV_BATTERY_CHARGE_RATE,
            CarSensorManager.SENSOR_RATE_NORMAL

        )



    }


    private fun watchBatteryLevel(sensorManager: CarSensorManager) {
        sensorManager.registerListener(
            { carSensorEvent ->

                var BatteryLevelTextView = findViewById<TextView>(R.id.BatteryLevelTextView)
                val batteryLevelInWH = carSensorEvent.floatValues[0]


                Log.i("battery level ",  batteryLevelInWH.toString() + "WH")
                BatteryLevelTextView.text = "BatteryLevel: " + batteryLevelInWH.toString() + "Wh"


            },
            CarSensorManager.SENSOR_TYPE_EV_BATTERY_LEVEL,
            CarSensorManager.SENSOR_RATE_NORMAL

        )


    }


    private fun watchIgnitionState(sensorManager: CarSensorManager) {
        sensorManager.registerListener(
            { carSensorEvent ->

                var IgnitionTextView = findViewById<TextView>(R.id.IgnitionTextView)
                var ignition = ""



                when (carSensorEvent.intValues[0]) {
                    CarSensorEvent.IGNITION_STATE_ACC -> ignition = "IGNITION_STATE_ACC"
                    CarSensorEvent.IGNITION_STATE_LOCK -> ignition = "IGNITION_STATE_LOCK"
                    CarSensorEvent.IGNITION_STATE_OFF -> ignition = "IGNITION_STATE_OFF"
                    CarSensorEvent.IGNITION_STATE_ON -> ignition = "IGNITION_STATE_ON"
                    CarSensorEvent.IGNITION_STATE_START -> ignition = "IGNITION_STATE_START"
                    CarSensorEvent.IGNITION_STATE_UNDEFINED -> ignition = "IGNITION_STATE_UNDEFINED"
                    else -> { // Note the block
                        ignition = "Gear not implemented"
                    }
                }
                IgnitionTextView.text = "Ignition Mode: " + ignition
                Log.i("igniton: ", ignition )
            },
            CarSensorManager.SENSOR_TYPE_IGNITION_STATE,
            CarSensorManager.SENSOR_RATE_NORMAL

        )


    }

    private fun watchParkingBreak(sensorManager: CarSensorManager) {
        sensorManager.registerListener(
            { carSensorEvent ->

                var parkingBreakTextView = findViewById<TextView>(R.id.ParkingBreakTextView )
                var parkingBreak = ""

                val parkingBreakOn: Boolean = carSensorEvent.intValues[0] == 1
                if (parkingBreakOn) {
                    parkingBreak = "on"

                }else {
                    parkingBreak = "off"
                }


                parkingBreakTextView.text = parkingBreak
                Log.i("break", parkingBreak)

            },
            CarSensorManager.SENSOR_TYPE_PARKING_BRAKE,
            CarSensorManager.SENSOR_RATE_NORMAL

        )


    }

    private fun watchFuelLevel(sensorManager: CarSensorManager) {
        sensorManager.registerListener(
            { carSensorEvent ->

                var fuelTextView = findViewById<TextView>(R.id.FuelTextView  )
                var fuel = carSensorEvent.floatValues[0].toString() + "milliliters"



                Log.i("fuel", fuel)
                fuelTextView.text = fuel

            },
            CarSensorManager.SENSOR_TYPE_FUEL_LEVEL,
            CarSensorManager.SENSOR_RATE_NORMAL

        )

    }


    private fun watchGearSensor(sensorManager: CarSensorManager) {
        sensorManager.registerListener(
        { carSensorEvent ->



            var gearTextView = findViewById<TextView>(R.id.gearTextView)
            var gear = ""

            when (carSensorEvent.intValues[0]) {
                CarSensorEvent.GEAR_DRIVE -> gear = "Gear: D"
                CarSensorEvent.GEAR_NEUTRAL -> gear = "Gear: N"
                CarSensorEvent.GEAR_REVERSE -> gear = "Gear: R"
                CarSensorEvent.GEAR_PARK -> gear = "Gear: P"
                else -> { // Note the block
                    gear = "Gear not implemented"
                }

            }

            gearTextView.text = gear
            Log.i("gear", gear)





        },
        CarSensorManager.SENSOR_TYPE_GEAR,
        CarSensorManager.SENSOR_RATE_NORMAL

        )


    }

    private fun watchSpeedMilage(sensorManager: CarSensorManager) {
    // TODO
    }


    private fun watchSpeedSensor(sensorManager: CarSensorManager ) {
        sensorManager.registerListener(
            { carSensorEvent ->

                var speedTextView = findViewById<TextView>(R.id.speedTextView)
                speedTextView.text = (("%.2f".format(carSensorEvent.floatValues[0] * 3.6)).toString() + "km/h")



            },
            CarSensorManager.SENSOR_TYPE_CAR_SPEED,
            CarSensorManager.SENSOR_RATE_NORMAL

        )
    }

    private fun watchOilLevel(sensorManager: CarSensorManager) {

        sensorManager.registerListener(
            { carSensorEvent ->
                println(carSensorEvent.toString())
                var engineOilTextView = findViewById<TextView>(R.id.engineOilTextView)

                engineOilTextView.text = "Engine Oil Level: " + carSensorEvent.toString();




            },
            CarSensorManager.SENSOR_TYPE_ENGINE_OIL_LEVEL,
            CarSensorManager.SENSOR_RATE_NORMAL




        )
    }

    private fun checkIfPermissionsGranted(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false

            }
        }
        return true

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var allPermissionsGranted = checkIfPermissionsGranted(grantResults)



        if (allPermissionsGranted && !car.isConnected && !car.isConnecting) {
            car.connect()
        }


    }
}
