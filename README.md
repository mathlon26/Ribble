# Ribble
[![Build](https://github.com/mathlon26/Ribble/actions/workflows/gradle.yml/badge.svg)](https://github.com/mathlon26/Ribble/actions/workflows/gradle.yml)\
[![GitHub forks](https://img.shields.io/github/forks/mathlon26/Ribble)](https://github.com/mathlon26/Ribble/network)\
[![GitHub stars](https://img.shields.io/github/stars/mathlon26/Ribble)](https://github.com/mathlon26/Ribble/stargazers)\
[![GitHub issues](https://img.shields.io/github/issues/mathlon26/Ribble)](https://github.com/mathlon26/Ribble/issues) \
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


**Ribble** is a (student) Java game engine project, designed as a learning / experimental engine for 2D graphics, rendering, input handling, and entity systems.

---

## Architecture & Project Structure

Ribble is organized into modular packages. The idea is to separate concerns cleanly, so that components like graphics, input, math, assets, ECS, and scenes are loosely coupled and easy to understand / extend.

Here’s a high-level overview of the project structure and how things fit together (things may change, a lot...):

```

ribble
├── assets
│   ├── AssetLoader.java
│   ├── AudioAssetLoader.java
│   ├── MaterialAssetLoader.java
│   ├── MeshAssetLoader.java
│   ├── ResourceManager.java
│   ├── ShaderAssetLoader.java
│   └── TextureAssetLoader.java
├── core
│   ├── Config.java
│   ├── Engine.java
│   ├── GameLoop.java
│   ├── info
│   │   └── Version.java
│   ├── RibbleGame.java
│   └── time
│       ├── Time.java
│       └── Timer.java
├── ecs
│   ├── component
│   ├── entity
│   └── system
├── graphics
│   ├── camera
│   ├── material
│   ├── mesh
│   ├── shader
│   ├── texture
│   └── Window\.java / Renderer / etc.
├── io
│   ├── input (listeners, devices, etc.)
│   └── output (audio, logger, etc.)
├── math
│   ├── geometry
│   ├── graphics math (projections, optics)
│   ├── numerics
│   ├── physics
│   ├── vector / matrix / quaternion / transform
│   └── MathUtils, etc.
├── scene
│   ├── Scene.java
│   ├── SceneManager.java
│   └── SceneNode.java
└── utils
├── FileUtils, ConfigLoader, Logger, etc.

```

### Key Components

- **core** — Bootstrapping, configuration, game loop, timing. This is the heart of the engine.
- **math** — Pure math utilities: vectors, matrices, geometry, numerics, physics math. No dependencies on graphics, or higher engine subsystems.
- **graphics** — Rendering, shaders, mesh loading, materials, textures, cameras.
- **io** — Input (keyboard, mouse, controllers, etc.), audio, and general output or logging.
- **assets** & **managers** — Loading, caching, and managing resources (textures, shaders, meshes, materials, audio, etc.).
- **ecs** — Entity-Component-System: entities, components, systems (render, physics, audio, input, etc.).
- **scene** — Scene graph or scene hierarchy; organizing what exists in the world / what gets rendered / updated.
- **utils** — Helper classes (file paths, config loaders, logging, misc).

---

## Build System

Ribble uses **Gradle** as its build system. Key info:

- There is a `build.gradle` in the root which defines dependencies, compile tasks, etc.
- The project is structured following the standard Gradle / Java conventions (`src/main/java/...`).
- You can build, run, and test using the usual Gradle tasks (e.g. `gradlew build`, `gradlew run`).
- Any external graphics / input / audio libraries used (if applicable) will be defined in `build.gradle`.

---

## Contributing & Learning

This repo is public and intended for learning. If you’re another student / contributor:

- You’re encouraged to **pick any `.java` file under `math/`** (or elsewhere) and try implementing or improving it.  
  Examples:
  - Write or improve `Interpolation`, `Noise`, `RootFinding` in `math.numerics`.
  - Extend geometry types like `Sphere`, `Plane`, `OrientedBox`.
  - Add new shader types, or improve material / rendering.
- Suggest features or bug-fixes via pull requests. Even small improvements to math accuracy, naming consistency, or performance are welcome.
- Try writing unit tests for math functions (e.g. verifying that matrix × vector gives expected result; that quaternion rotations are correct, etc.).
- Please follow the java coding rules clearly stated in the slides on blackboard by Kris Luyten (14oopregels.pdf).

---

## Goals & Ideas

Some of the learning goals / potential future enhancements:

- Clean, maintainable code structure; separation of concerns.
- Stable rendering pipeline: cameras (2D, 3D), meshes, materials, shaders.
- A working entity system to allow flexible architectures.
- Input abstraction: mouse, keyboard, touchpad, controller, microphone listeners.
- Math library that supports geometry, physics, numerics, etc.
- Possibly extend with audio, scene management, animations, maybe networking / UI.

---

## License
MIT License

Copyright (c) 2025 Mathijs Follon

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

Thank you for dropping by Ribble!  
Feel free to explore, break things, improve them. Learning happens by doing!
