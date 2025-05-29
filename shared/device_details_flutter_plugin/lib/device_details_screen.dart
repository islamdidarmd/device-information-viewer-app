import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class DeviceDetailsScreen extends StatefulWidget {
  const DeviceDetailsScreen({super.key});

  @override
  State<DeviceDetailsScreen> createState() => _DeviceDetailsScreenState();
}

class _DeviceDetailsScreenState extends State<DeviceDetailsScreen> {
  static const platform = MethodChannel('com.example.device_details_viewer/device_info');

  String? manufacturer;
  String? model;
  String? osVersion;
  String? sdkVersion;
  String? error;

  Future<void> _getDeviceDetails() async {
    try {
      final result = await platform.invokeMethod('getDeviceInfo');
      setState(() {
        manufacturer = result['manufacturer'] as String?;
        model = result['model'] as String?;
        osVersion = result['osVersion'] as String?;
        sdkVersion = result['sdkVersion'] as String?;
        error = null;
      });
    } on PlatformException catch (e) {
      setState(() {
        error = e.message;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
        padding: const EdgeInsets.all(24.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Center(
              child: ElevatedButton(
                onPressed: _getDeviceDetails,
                child: const Text('Get Device Details'),
              ),
            ),
            const SizedBox(height: 32),
            Text('Manufacturer: ${manufacturer ?? '-'}'),
            Text('Model: ${model ?? '-'}'),
            Text('OS Version: ${osVersion ?? '-'}'),
            Text('SDK Version: ${sdkVersion ?? '-'}'),
            if (error != null) ...[
              const SizedBox(height: 16),
              Text('Error: $error', style: const TextStyle(color: Colors.red)),
            ],
          ],
        ),

    );
  }
}
