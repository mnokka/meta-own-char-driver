cd YOCTO
source poky/oe-init-build-env BUILD-MYCHARDRIVER


luo build-hakemiston BUILD-MYCHARDRIVER (tai käyttää sitä jos se jo on)

siirtää sinut sinne automaattisesti

asettaa tarvittavat ympäristömuuttujat
-------------

conf/layer.conf

# Layer configuration for meta-own-char-driver

BBPATH .= ":${LAYERDIR}"
BBFILES += "${LAYERDIR}/recipes-*/*/*.bb \
            ${LAYERDIR}/recipes-*/*/*.bbappend"

LAYERNAME = "meta-own-char-driver"

BBFILE_COLLECTIONS += "meta-own-char-driver"
BBFILE_PATTERN_meta-own-char-driver = "^${LAYERDIR}/"
BBFILE_PRIORITY_meta-own-char-driver = "6"

-----------


bitbake-layers add-layer ../meta-own-char-driver


mika@mika-ThinkCentre-M710q:/media/mika/YOCTO_T74/YOCTO/BUILD-MYCHARDRIVER$ bitbake-layers show-layers
NOTE: Starting bitbake server...
layer                 path                                      priority
==========================================================================
meta                  /media/mika/YOCTO_T74/YOCTO/poky/meta     5
meta-poky             /media/mika/YOCTO_T74/YOCTO/poky/meta-poky  5
meta-yocto-bsp        /media/mika/YOCTO_T74/YOCTO/poky/meta-yocto-bsp  5
meta-oe               /media/mika/YOCTO_T74/YOCTO/meta-openembedded/meta-oe  6

------------------------------




DESCRIPTION = "Minimal char device driver returning names/strings"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Lähdekoodit – ne on tallennettu reseptin files/-kansioon
SRC_URI = "file://mychardriver.c \
           file://Makefile"

# Työtilan juuri on juuri se hakemisto, jossa Makefile sijaitsee
S = "${WORKDIR}"
export S = "${WORKDIR}"
DEPENDS += "virtual/kernel"

inherit module

# (valinnainen) varmista, että M‑parametri on eksplisiittisesti asetettu
EXTRA_OEMAKE = "M=${S}"

# Ohitetaan turha configure‑vaihe (kernel‑moduulille ei tarvita)
do_configure[noexec] = "1"

do_install() {
    # Luo kohdehakemisto, jos sitä ei vielä ole
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra

    # Kopioi .ko‑tiedoston oikeaan paikkaan
    install -m 0644 mychardriver.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

# Pakettien tiedostoluettelo
FILES_${PN} += "/lib/modules/${KERNEL_VERSION}/extra/mychardriver.ko"