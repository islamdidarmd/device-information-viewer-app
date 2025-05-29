// Entry point for the Flutter module
import 'package:device_details_flutter_plugin/device_details_screen.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MaterialApp(
    home: FlutterModuleHomeScreen(),
    debugShowCheckedModeBanner: false,
  ));
}

class FlutterModuleHomeScreen extends StatelessWidget {
  const FlutterModuleHomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Flutter Module Home'),
      ),
      body: DeviceDetailsScreen(),
    );
  }
}
