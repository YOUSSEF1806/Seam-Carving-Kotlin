calculate the energy for each pixel of the image. Energy is the pixel's importance. The higher the pixel energy, the less likely this pixel is to be removed from the picture while reducing.

There are several different energy functions invented for seam carving. In this project, we will use dual-gradient energy function.

The energy of the pixel(x, y) is E(x, y) = sqrt( Δx(x, y)^2 + Δy(x, y)^2 )

# **Objectif:**

Calculate energies for all pixels of the image. Normalize calculated energies using the following formula:
intensity = (255.0 * energy / maxEnergyValue).toInt()

To display energy as a grey-scale image, set color components of the output image pixels to calculated intensity. For example, (red = intensity, green = intensity, blue = intensity).

You should use double precision in this and in the following stages.
