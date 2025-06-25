# README
Essentially, this line adds a reverse path that doesn't add any extra flow capacity. 

Also, it allows for:
* Backwards Residual Capacity representation
* Cancellation of pushed flow if a better path is found.
* Creating a bidirectional residual graph.
* Dynamically adjusting flow.

If it didn't exist:
* The search in <code>hasAugmentingPath</code> couldn't compare flow in the reverse direction
* Algorithm couldn't undo inefficient paths, resulting in suboptimal results.  