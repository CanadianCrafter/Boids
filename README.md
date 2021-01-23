# Boids
Boids is an artificial life program, which simulates the flocking behavior of birds and fish. However, it doesn't do this with a complex algorithm, but a handful of basic rules that each of the boids follow. Thus, they are an example of emergent behavior. The rules they follow are: 

* Separation: Steer to avoid crowding local flock mates. 

* Alignment: Steer towards the average heading of local flock mates. 

* Cohesion: Steer to move towards the average position (center of mass) of local flock mates. 

* Obstacle avoidance: Steer away from obstacles. 

* Goal seeking: Steer towards a goal. (Not implemented yet).

You can learn more about it on Wikipedia: https://en.wikipedia.org/wiki/Boids.

This program aims to do recreate just that and will follow the pseudocode from http://www.kfish.org/boids/pseudocode.html. Additional features that make the program more like a simulation might get added.
