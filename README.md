# Jenkins IPv4 Parameter Plugin

A Jenkins plugin that provides IPv4 address parameter type for build configurations.

## Overview

This plugin extends Jenkins by adding a specialized parameter type for IPv4 addresses, allowing users to define and validate IPv4 address inputs in their build parameters.

## Features

- **IPv4 Parameter Type**: Adds a new parameter type specifically for IPv4 addresses
- **Input Validation**: Ensures that only valid IPv4 addresses are accepted
- **Jenkins Integration**: Seamlessly integrates with Jenkins build parameter system
- **User-friendly UI**: Provides intuitive interface for IPv4 parameter configuration

## Technical Details

- **Language**: Java 11
- **Build Tool**: Maven
- **Framework**: Jenkins Plugin Framework
- **Jenkins Version**: 2.387.3+
- **Package Format**: HPI (Jenkins Plugin)
- **License**: MIT License

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── io/jenkins/plugins/ipv4parameter/
│   │       └── IPv4ParameterDefinition.java
│   └── resources/
│       ├── index.jelly
│       └── io/jenkins/plugins/ipv4parameter/
│           └── IPv4ParameterDefinition/
│               ├── config.jelly
│               └── index.jelly
```

## Building

To build the plugin:

```bash
mvn clean package
```

The built plugin (`.hpi` file) will be available in the `target/` directory.

## Installation

1. Build the plugin using Maven
2. Upload the generated `.hpi` file to Jenkins via "Manage Jenkins" > "Manage Plugins" > "Advanced"
3. Restart Jenkins

## Usage

1. In your Jenkins job configuration, add a new parameter
2. Select "IPv4 Parameter" from the parameter type dropdown
3. Configure the parameter name and default value
4. The parameter will validate IPv4 address format during build execution

![Add Parameter](Add%20Parameter.png)

![Add Parameter 2](Add%20Parameter%202.png)

![Build with Parameters](Build%20with%20Parameters.png)

## Development

This project follows standard Jenkins plugin development practices:

- Uses Jenkins Plugin Parent POM
- Implements `ParameterDefinition` for custom parameter types
- Includes Jelly templates for UI rendering
- Supports Java 8, 11, and newer versions

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
