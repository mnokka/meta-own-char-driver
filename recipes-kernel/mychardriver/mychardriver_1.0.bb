# =============================================================================
# mychardriver – a tiny char driver that prints a static string
# =============================================================================

DESCRIPTION = "Simple character driver that returns a static string"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Pull the source files from the layer's files/ directory
SRC_URI = "file://mychardriver.c \
           file://Makefile"

# After unpack, the sources are placed directly in ${WORKDIR}
S = "${WORKDIR}"

# Use Yocto's kernel‑module helper class (provides do_compile, do_install, etc.)
inherit module

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 0644 ${WORKDIR}/mychardriver.ko \
        ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}


# Automatically load the module on boot (creates /etc/modules-load.d/mychardriver.conf)
KERNEL_MODULE_AUTOLOAD += "mychardriver"

# No extra kernel config symbols are required for this driver.
# If you ever need them, add a fragment and reference it:
# KERNEL_CONFIG_FRAGMENTS += "mydriver.cfg"
