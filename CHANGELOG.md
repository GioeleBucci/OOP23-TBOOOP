## v1.1

### New features
- Added a new boss: Duke of Eyes!

### Tweaks and changes
- Increased invincibility duration after being hit by an attack to .5 seconds

### Bugfixes
- Fixed a bug with inconsistent projectile speed

### Dev notes 
- Added a new `Attack` class that features different attack patterns that can be used by any generic entity.
- Removed executable jar from tracked files (it can be accessed via the releases page)
- Fixed broken spicy pepper test
- Refactored enemy projectile spawn in the decorators to use the generic methods provided by the `Attack` class
