#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "depthai-ros-msgs robot library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "depthai-ros"
ROS_BPN = "msgs"

ROS_BUILD_DEPENDS = " \
    builtin-interfaces \
    std-msgs \
    geometry-msgs \
    vision-msgs \
    sensor-msgs \
    rosidl-default-generators \
    rosidl-adapter \
    ros-environment \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \ 
    ament-cmake-ros \
    ament-cmake-gmock \
    ament-cmake-gtest \
    ament-cmake-pytest \
"

ROS_EXPORT_DEPENDS = " \
    rosidl-default-runtime \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    builtin-interfaces \
    rosidl-default-runtime \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

# No module named rosidl_adapter
export PYTHONPATH
PYTHONPATH = "/home/put/.local/lib/python3.9/site-packages:/opt/ros/foxy/lib/python3.8/site-packages"

#
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# # Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# # don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"
#
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

FILES:${PN}:prepend = " \
    ${datadir}/depthai_ros_msgs \
    /usr/lib/python3.9/site-packages/depthai_ros_msgs \
"

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/lib*${SOLIBSDEV} \
"

ROS_BRANCH ?= "branch=ros-release"
SRC_URI = "git://github.com/luxonis/depthai-ros;${ROS_BRANCH};protocol=https"
SRCREV = "af3a1ebff8d3ec8eecdece344d1250805294d520"

S = "${WORKDIR}/git/depthai_ros_msgs"


ROS_BUILD_TYPE = "ament_cmake"


inherit ros_${ROS_BUILD_TYPE}
