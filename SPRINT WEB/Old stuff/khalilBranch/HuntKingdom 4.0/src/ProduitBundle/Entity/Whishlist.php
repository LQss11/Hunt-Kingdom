<?php

namespace ProduitBundle\Entity;
use MainBundle\Entity\User;
use Doctrine\ORM\Mapping as ORM;

/**
 * Whishlist
 *
 * @ORM\Table(name="whishlist")
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\WhishlistRepository")
 */
class Whishlist
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity="Produit")
     * @ORM\JoinColumn(name="idProduit", referencedColumnName="id")
     */
    private $idProduit;
    /**
     * @ORM\ManyToOne(targetEntity="MainBundle\Entity\User")
     * @ORM\JoinColumn(name="idClient", referencedColumnName="id")
     */
    private $idClient;

    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idProduit
     *
     * @param \ProduitBundle\Entity\Produit $idProduit
     *
     * @return Whishlist
     */
    public function setIdProduit(\ProduitBundle\Entity\Produit $idProduit = null)
    {
        $this->idProduit = $idProduit;

        return $this;
    }

    /**
     * Get idProduit
     *
     * @return \ProduitBundle\Entity\Produit
     */
    public function getIdProduit()
    {
        return $this->idProduit;
    }

    /**
     * Set idClient
     *
     * @param \MainBundle\Entity\User $idClient
     *
     * @return Whishlist
     */
    public function setIdClient(\MainBundle\Entity\User $idClient = null)
    {
        $this->idClient = $idClient;

        return $this;
    }

    /**
     * Get idClient
     *
     * @return \MainBundle\Entity\User
     */
    public function getIdClient()
    {
        return $this->idClient;
    }
}
