#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "depthai-ros-bridge robot library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "depthai-ros"
ROS_BPN = "bridge"

ROS_BUILD_DEPENDS = " \
    cv-bridge \
    camera-info-manager \
    depthai-core \
    depthai-ros-msgs \
    image-transport \
    ros-environment \
    std-msgs \
    geometry-msgs \
    vision-msgs \
    sensor-msgs \
    stereo-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    robot-state-publisher \
    xacro \
"

export ROS_DISTRO
ROS_DISTRO = "foxy"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""
#
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# # Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# # don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"
#
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

FILES:${PN}:prepend = " \
    ${datadir}/depthai_bridge \
"

# ERROR: husky-base-1.0-r0 do_package_qa: QA Issue: -dev package husky-base-dev contains non-symlink .so '/usr/lib/libhusky_hardware.so' [dev-elf]
FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/lib*${SOLIBSDEV} \
"

ROS_BRANCH ?= "branch=foxy"
SRC_URI = "git://github.com/luxonis/depthai-ros;${ROS_BRANCH};protocol=https"
SRCREV = "ebc6d6ff1fbd79829c71d4609a581043774c30e0"

S = "${WORKDIR}/git/depthai_bridge"


ROS_BUILD_TYPE = "ament_cmake"


inherit ros_${ROS_BUILD_TYPE}
