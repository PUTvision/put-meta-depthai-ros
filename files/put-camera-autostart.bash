sleep 30

export ROS_DOMAIN_ID=21
export AMENT_PREFIX_PATH=/usr
export ROS_OS_OVERRIDE=openembedded
export ROS_DISTRO=foxy
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:/usr/lib/"
export PKG_CONFIG_PATH="/usr/lib/pkgconfig:/usr/share/pkgconfig"

source /usr/bin/ros_setup.bash

ros2 launch depthai_cognition rgb_depth_publisher.launch.py
