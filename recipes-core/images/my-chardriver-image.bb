DESCRIPTION = "Yocto image with my custom name driver"
LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL:append = " mychardriver"
